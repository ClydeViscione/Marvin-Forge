folder('Tools') {
    description('Folder for miscellaneous tools.')
}

job('Tools/clone-repository') {
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }

    wrappers {
        preBuildCleanup()
    }

    steps {
        shell('git clone "$GIT_REPOSITORY_URL" .')
    }

    triggers {}

    disabled(false)
}

job('Tools/SEED') {
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }

    steps {
        jobDsl {
            scriptText('''
                job("$DISPLAY_NAME") {
                    scm {
                        github("$GITHUB_NAME")
                    }

                    triggers {
                        scm('* * * * *')
                    }

                    wrappers {
                        preBuildCleanup()
                    }

                    steps {
                        shell('make fclean')
                        shell('make')
                        shell('make tests_run')
                        shell('make clean')
                    }
                }
            '''.stripIndent())
            
          
        }
    }

}