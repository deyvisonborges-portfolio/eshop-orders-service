#!/bin/bash

# Função para verificar se um container está em execução
check_container() {
  local container_name=$1
  local retries=3
  local count=0

  while [ $count -lt $retries ]; do
    if [ "$(docker ps -q -f name=$container_name)" ]; then
      echo "$container_name is running"
      return 0
    else
      echo "Waiting for $container_name to be up..."
      sleep 5
      ((count++))
    fi
  done

  echo "Error: $container_name is not running"
  return 1
}

# Verificar cada container
containers=("redis" "mongo" "postgres" "rabbitmq")

for container in "${containers[@]}"; do
  check_container $container
  if [ $? -ne 0 ]; then
    echo "Exiting due to $container not being available"
    exit 1
  fi
done

echo "All containers are running. Starting the application..."
