if [ -d "/opt/podman" ]; then
	export PATH="/opt/podman/bin:$PATH"
	alias docker="podman"
fi
