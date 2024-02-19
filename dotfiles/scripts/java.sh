if type java > /dev/null && type javac > /dev/null; then
	JAVA_VERSION=$(java -version 2>&1 | grep -Eow -m 1 "[0-9]+\.[0-9]+\.[0-9]+")
	JAVA_MAJOR_VERSION=$(echo "$JAVA_VERSION" | cut -d . -f 1)
	if [ -d "/usr/local/opt/openjdk@$JAVA_MAJOR_VERSION" ]; then
		export PATH="/usr/local/opt/openjdk@$JAVA_MAJOR_VERSION/bin:$PATH"
		export JAVA_HOME="/usr/local/Cellar/openjdk@$JAVA_MAJOR_VERSION/$JAVA_VERSION/libexec/openjdk.jdk/Contents/Home"
		export JAVA=true
	fi
fi
