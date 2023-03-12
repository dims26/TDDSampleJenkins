node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/dims26/TDDSampleJenkins.git'
  }

  stage("Build project with test execution") {
    sh "./gradlew build"
  }
}