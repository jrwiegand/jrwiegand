## replace vim with neovim
export VIM=false

if type nvim > /dev/null; then
	alias vim="nvim"
	export NEOVIM=true
elif type vim > /dev/null; then
	export VIM=true
fi
