export UPDATE_BUN=false
if type bun > /dev/null; then
	# completions
	[ -s "/Users/jwiegand/.bun/_bun" ] && source "/Users/jwiegand/.bun/_bun"
	export UPDATE_BUN=true
fi

export UPDATE_NVM=false
if [ -d "$HOME/.nvm" ]; then
	export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
	export UPDATE_NVM=true
fi
