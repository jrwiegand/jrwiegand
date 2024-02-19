ZSH_THEME="ys"
ENABLE_CORRECTION="true"
COMPLETION_WAITING_DOTS="true"
HIST_STAMPS="%Y-%m-%dT%T"
HOMEBREW_NO_ANALYTICS=1

export ZSH="$HOME"/.oh-my-zsh
export UPDATE_ZSH_DAYS=7
export DOTFILES="$HOME"/Dev/jrwiegand/dotfiles
export PATH="/usr/local/bin:/usr/local/sbin:/usr/sbin:/usr/bin:/sbin:/bin"

plugins=(
    history-substring-search
)

source "$ZSH"/oh-my-zsh.sh

source "$DOTFILES"/environments/*.sh

# disable auto correct
unsetopt correct_all
