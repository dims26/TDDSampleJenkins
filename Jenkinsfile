node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/dims26/TDDSampleJenkins.git'
  }

  stage("Build project with test execution") {
    sh "./gradlew build"
  }

  stage("Deploy to DockerHub with Jib") {
    withCredentials([string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD'), string(credentialsId: 'DOCKER_USERNAME', variable: 'DOCKER_USERNAME')]) {
        sh '''
        echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
        '''
        sh '''
        ./gradlew jib \
        -Djib.to.auth.username=${DOCKER_USERNAME} \
        -Djib.to.auth.password=${DOCKER_PASSWORD}
        '''
    }
  }
}