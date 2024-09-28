#!/bin/bash

environment="$1"

if [ -z "$environment" ]; then
  echo "Environment is required. Please provide it as the first argument."
  exit 1
fi

if [ "$environment" == "prod" ]; then
  version="tms-prod1.0.0"
  rm keycloak.cert
  mv keycloak_prod.cert keycloak.cert


elif [ "$environment" == "coll" ]; then
  version="latest"
else
  echo "Invalid environment. Please specify 'prod' or 'coll'."
  exit 1
fi

echo "OrderManager MS"
echo "Creating and pushing version = $version for environment = $environment"

cd ..

mvn clean package

cp target/*.jar docker

cd docker

docker build -t kasanova_dummy_order .

case "$environment" in
  "prod")
    docker tag kasanova_dummy_order salk94/kasanova_dummy_order:$version
    docker salk94/kasanova_dummy_order:$version
    ;;
  "coll")
    docker tag kasanova_dummy_order salk94/kasanova_dummy_order:$version
    docker push salk94/kasanova_dummy_order:$version
    ;;
  *)
    echo "Invalid environment. Please specify 'prod' or 'coll'."
    exit 1
    ;;
esac

rm *.jar

git checkout .
