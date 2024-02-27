export RBENV=false
if type rbenv > /dev/null; then
	eval "$(rbenv init - zsh)"
	export RBENV=true
fi
