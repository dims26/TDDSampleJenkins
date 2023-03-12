node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/dims26/TDDSampleJenkins.git'
  }

  stage("Build project with test execution") {
    sh "./gradlew build"
  }

  stage("Deploy to DockerHub with Jib") {
    withCredentials([script.string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD'), script.string(credentialsId: 'DOCKER_USERNAME', variable: 'DOCKER_USERNAME')]) {
        echo "${this.script.DOCKER_PASSWORD}" | docker login -u "${this.script.DOCKER_USERNAME}" --password-stdin
        sh "./gradlew jib"
    }
  }
}