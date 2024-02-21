# >>> conda initialize >>>
# !! Contents within this block are managed by 'conda init' !!
__conda_setup="$('/Users/jwiegand/Dev/anaconda3/bin/conda' 'shell.zsh' 'hook' 2> /dev/null)"
if [ $? -eq 0 ]; then
    eval "$__conda_setup"
else
    if [ -f "/Users/jwiegand/Dev/anaconda3/etc/profile.d/conda.sh" ]; then
        . "/Users/jwiegand/Dev/anaconda3/etc/profile.d/conda.sh"
    else
        export PATH="/Users/jwiegand/Dev/anaconda3/bin:$PATH"
    fi
fi
unset __conda_setup
# <<< conda initialize <<<

if [ -d "$HOME/.pyenv" ]; then
	## To avoid them accidentally linking against a Pyenv-provided Python
	## https://github.com/pyenv/pyenv#getting-pyenv
	alias brew='env PATH="${PATH//$(pyenv root)\/shims:/}" brew'

	export PYENV_ROOT="$HOME/.pyenv"
	command -v pyenv >/dev/null || export PATH="$PYENV_ROOT/bin:$PATH"
	eval "$(pyenv init -)"
fi
