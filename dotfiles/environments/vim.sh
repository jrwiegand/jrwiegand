## replace vim with neovim
export UPDATE_HX=false
export UPDATE_NEOVIM=false
export UPDATE_VIM=false

if type hx > /dev/null; then
	alias vim="hx"
elif type nvim > /dev/null; then
	alias vim="nvim"
	export UPDATE_NEOVIM=true
elif type vim > /dev/null; then
	export UPDATE_VIM=true
fi
