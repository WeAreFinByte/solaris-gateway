run_uaa:
	docker pull cfidentity/uaa:latest \
	&& docker run \
		--detach \
		--mount type=bind,source=${PWD}/uaa/uaa.yml,target=/uaa.yml \
		--env CLOUDFOUNDRY_CONFIG_PATH= \
		--env spring_profiles=default,hsqldb \
		-p 8080:8080 \
		--name uaatest \
		cfidentity/uaa:latest

build_gateway:
	docker build -t sbg .

run_gateway:
	docker run sbg

test_integration:
	docker-compose -f integration-tests/docker-compose.yml build --no-cache && \
	docker-compose -f integration-tests/docker-compose.yml up --abort-on-container-exit