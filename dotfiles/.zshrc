ZSH_THEME="ys"
ENABLE_CORRECTION="true"
COMPLETION_WAITING_DOTS="true"
HIST_STAMPS="%Y-%m-%dT%T"

export ZSH="$HOME"/.oh-my-zsh
export UPDATE_ZSH_DAYS=7
export DOTFILES="$HOME"/Dev/jrwiegand/dotfiles

export PATH="/usr/sbin:/usr/bin:/sbin:/bin"

plugins=(
	history-substring-search
)

source "$ZSH"/oh-my-zsh.sh

# Source all the environment files
for environment_file in "$DOTFILES"/environments/*.sh; do source $environment_file; done

# Source all the function files
for function_file in "$DOTFILES"/functions/*.sh; do source $function_file; done

# disable auto correct
unsetopt correct_all
