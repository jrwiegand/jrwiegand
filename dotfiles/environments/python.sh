if [ -d "$HOME/.pyenv" ]; then
	## To avoid them accidentally linking against a Pyenv-provided Python
	## https://github.com/pyenv/pyenv#getting-pyenv
	alias brew='env PATH="${PATH//$(pyenv root)\/shims:/}" brew'

	export PYENV_ROOT="$HOME/.pyenv"
	[[ -d $PYENV_ROOT/bin ]] && export PATH="$PYENV_ROOT/bin:$PATH"
	eval "$(pyenv init -)"
fi
