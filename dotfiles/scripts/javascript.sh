export BUN=false
if type bun > /dev/null; then
	export BUN=true
fi

export NVM=false
if [ -d "$HOME/.nvm" ]; then
	export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
	export NVM=true
fi
