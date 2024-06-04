#!/bin/sh
# Define o perfil de execução (dev por padrão)
PROFILE=${1:-dev}
echo "Iniciando aplicação com o perfil: $PROFILE"
export SPRING_PROFILES_ACTIVE=$PROFILE
mvn spring-boot:run