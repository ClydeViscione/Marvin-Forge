FROM jenkins/jenkins:lts

RUN jenkins-plugin-cli \
  --plugins \
  github \
  cloudbees-folder \
  configuration-as-code \
  credentials \
  instance-identity \
  job-dsl \
  script-security \
  structs \
  role-strategy \
  ws-cleanup 

COPY my_marvin.yml /var/jenkins_home/my_marvin.yml
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/my_marvin.yml


COPY job_dsl.groovy /var/jenkins_home/job_dsl.groovy
