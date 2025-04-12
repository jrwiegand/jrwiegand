export UPDATE_COMPOSER=false
if type composer > /dev/null; then
	if [ -d "$HOME/.composer" ]; then
		export PATH="$HOME/.composer/vendor/bin:$PATH"
		export UPDATE_COMPOSER=true
	fi

	if [ -d "$HOME/.config/composer" ]; then
		export PATH="$HOME/.config/composer/vendor/bin:$PATH"
		export UPDATE_COMPOSER=true
	fi
fi
