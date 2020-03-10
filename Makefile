uaa:
	docker pull cfidentity/uaa:latest \
	&& docker run \
		--detach \
		--mount type=bind,source=${PWD}/uaa/uaa.yml,target=/uaa.yml \
		--env CLOUDFOUNDRY_CONFIG_PATH= \
		--env spring_profiles=default,hsqldb \
		-p 8080:8080 \
		--name uaatest \
		cfidentity/uaa:latest