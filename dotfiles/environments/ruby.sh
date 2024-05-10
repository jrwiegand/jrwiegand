export UPDATE_RBENV=false
if type rbenv > /dev/null; then
	eval "$(rbenv init - zsh)"
	export UPDATE_RBENV=true
fi
