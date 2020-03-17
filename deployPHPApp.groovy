node(labels: 'master'){
    stage('Get repo'){
        println('Stage: ')
        sh "rm -rf ${WORKSPACE}/*"
        sh "mkdir ${WORKSPACE}/build"
        dir('build'){
            git branch: "${REPO_BRANCH}", url: "git@github.com:swabb/phpapp.git"
        }
    }
}
