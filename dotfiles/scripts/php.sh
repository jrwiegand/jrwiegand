export COMPOSER=false
if type composer > /dev/null; then
	if [ -d "$HOME/.composer" ]; then
		export PATH="$HOME/.composer/vendor/bin:$PATH"
		export COMPOSER=true
	fi
fi
