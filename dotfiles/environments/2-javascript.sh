export UPDATE_BUN=false
if type bun > /dev/null; then
	# completions
	[ -s "/Users/jwiegand/.bun/_bun" ] && source "/Users/jwiegand/.bun/_bun"
	export UPDATE_BUN=true
fi

export UPDATE_FNM=false
if type fnm > /dev/null; then
	export PATH="/Users/jwiegand/.local/state/fnm_multishells/94116_1756347173764/bin":$PATH
	export FNM_MULTISHELL_PATH="/Users/jwiegand/.local/state/fnm_multishells/94116_1756347173764"
	export FNM_VERSION_FILE_STRATEGY="local"
	export FNM_DIR="/Users/jwiegand/.local/share/fnm"
	export FNM_LOGLEVEL="info"
	export FNM_NODE_DIST_MIRROR="https://nodejs.org/dist"
	export FNM_COREPACK_ENABLED="false"
	export FNM_RESOLVE_ENGINES="true"
	export FNM_ARCH="arm64"
	rehash

	eval "$(fnm env --use-on-cd --shell zsh)"

	export UPDATE_FNM=true
fi

export UPDATE_NVM=false
if [ -d "$HOME/.nvm" ]; then
	export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" # This loads nvm
	export UPDATE_NVM=true
fi
