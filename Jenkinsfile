node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/dims26/TDDSampleJenkins.git'
  }

  stage("Build project with test execution") {
    sh "./gradlew build"
  }

  stage("Deploy to DockerHub with Jib") {
    echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
    sh "./gradlew jib"
  }
}