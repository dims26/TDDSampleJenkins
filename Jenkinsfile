node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/dims26/TDDSampleJenkins.git'
  }

  stage("Build project with test execution") {
    sh "./gradlew build"
  }

  stage("Deploy to DockerHub with Jib and to Heroku") {
    parallel([
        docker: {
            withCredentials([string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD'), string(credentialsId: 'DOCKER_USERNAME', variable: 'DOCKER_USERNAME')]) {
                sh '''
                echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
                ./gradlew jib -Djib.to.auth.username="${DOCKER_USERNAME}" -Djib.to.auth.password="${DOCKER_PASSWORD}"
                '''
            }
        },
        heroku: {
            withCredentials([string(credentialsId: 'HEROKU_KEY', variable: 'HEROKU_KEY')]) {
                withEnv(["HEROKU_API_KEY=${HEROKU_KEY}"]) {
                    sh '''
                    echo $HEROKU_API_KEY
                    ./gradlew deployHeroku
                    '''
                }
            }
        }
    ])
  }

  jacoco(
    execPattern: '**/*.exec',
    sourcePattern: 'src/main/java',
    exclusionPattern: 'src/test*'
  )
}