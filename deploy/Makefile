.PHONY: deps_repos obs_start obs_stop app_start app_stop env_start env_stop app_start app_build

app_start:
	kubectl apply -f ../kubernetes/deployment-app.yaml
app_stop:
	kubectl delete -f ../kubernetes/deployment-app.yaml --force
app_build:
	eval  $$(minikube docker-env); \
	mvn -f ../pom.xml clean install -PbuildDocker -Dmaven.test.skip=true
env_start:
	helm dependency update ./env
	helm upgrade --install env ./env
env_stop:
	helm delete env
deps_repos:
	echo "Updating repos"
	helm repo add grafana https://grafana.github.io/helm-charts
	helm repo add vector https://helm.vector.dev
	helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
	helm repo add vm https://victoriametrics.github.io/helm-charts/
	helm repo add open-telemetry https://open-telemetry.github.io/opentelemetry-helm-charts
	helm repo add bitnami https://charts.bitnami.com/bitnami
	helm repo add kafka-ui https://provectus.github.io/kafka-ui-charts
obs_start:
	helm dependency update ./obs
	helm upgrade --install obs ./obs

obs_stop:
	helm delete obs