job('Java Maven App DSL 2') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/ismaBlazquez/CursoJenkins-simple-java-maven-app.git', 'master') { node ->
            node / gitConfigName('ismaBlazquez')
            node / gitConfigEmail('ismatrompeta4@gmail.com')
        }
    }
    steps {
        maven {
          mavenInstallation('mavenjenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenjenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/jenkins_home/workspace/Java Maven App DSL 2/target/my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
    }
}