# Define variáveis
JAR_FILE=target/orders-application.jar

# Tarefa para compilar o projeto
build:
	mvn clean package

# Tarefa para executar a aplicação com um perfil específico
run:
	./start.sh $(PROFILE)

# Tarefa para ambiente de desenvolvimento
run-dev:
	$(MAKE) PROFILE=dev run

# Tarefa para ambiente de staging
run-staging:
	$(MAKE) PROFILE=staging run

# Adicione outras tarefas conforme necessário
# run-production:
# 	$(MAKE) PROFILE=production run

# Tarefa padrão
.PHONY: default build run run-dev run-staging
default: run-dev
