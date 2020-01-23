#### general
ZSH_THEME="ys"
ENABLE_CORRECTION="true"
COMPLETION_WAITING_DOTS="true"
HIST_STAMPS="yyyy.mm.dd"

export ZSH=$HOME/.oh-my-zsh
export UPDATE_ZSH_DAYS=7
export NVM_LAZY_LOAD=true

plugins=(
    history-substring-search
    zsh-nvm
    zsh-navigation-tools
    zsh_reload
)

source "$ZSH"/oh-my-zsh.sh

unsetopt correct_all

#### aliases
# be very careful with this
alias destroy="rm -rf"
alias sudo-destroy="sudo rm -rf"

# replace vim with neovim
alias vim="nvim"

# copy all!
alias copy="cp -R"

# extract the file from the tar
alias untar="tar -zxvf"

# start a static python server
alias pyserv="python -m SimpleHTTPServer 7977"

# start a static node server
alias jsserv="nvm use lts/* && serve"

# update list of brew installed formula
alias update-brew-list='brew ls --versions > "$HOME"/dotfiles/brew.txt && brew cask ls --versions > "$HOME"/dotfiles/brew-casks.txt'

# install npm packages from a list
alias install-npm-packages='cat "$HOME"/dotfiles/npm.txt | xargs npm install --global'

# update os
alias update-os='echo "Updating macOS..." && softwareupdate -i -a'

# update npm
alias update-npm='echo "Updating npm..." && npm update npm -g && npm update -g'

# update brew
alias update-brew='echo "Updating brew..." && brew update && brew upgrade && brew cleanup && brew doctor --verbose --debug'

# update brew cask
alias update-brew-cask='echo "Updating brew cask..." && brew cleanup && brew cask doctor --verbose --debug && brew cask outdated --greedy --verbose --debug'

# update all
alias update-all="update-os; update-npm; update-brew; update-brew-cask"

# did.txt
alias did='vim +"normal Go" +"r!date" +"put_" "$HOME"/did.txt'

#### functions
# find all the things
sudo-search-for-file() { sudo find "$1" -iname "$2"; }
search-for-file() { find "$1" -iname "$2"; }

# find all the things in files
sudo-search-for-string() { sudo rg -F -i -p "$1" "$2"; }
search-for-string() { rg -F -i -p "$1" "$2"; }

# delete all files with this name
sudo-destroy-all-files() { sudo find / -name "$1" -type f -delete; }
destroy-all-files() { find ~/ -name "$1" -type f -delete; }

# change the owner of the database by replacing all the instances with sed
change-db-owner(){
    find "$1" -type f -exec sed -i "" "s/ Owner: $2/  Owner: $3/g" {} \;
    find "$1" -type f -exec sed -i "" "s/ $2;/ $3;/g" {} \;
}

# edit file or files with sed
edit-file(){ find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \; }

# edit file that requires root permission
sudo-edit-file() { sudo find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \; }

# psql function to clear the database to a usable state
reset-db() { dropdb "$1" && createdb "$1" && psql "$1" -f "$2"; }

# psql function to create a new db with the given .sql file
new-db() { createdb "$1" && psql "$1" -f "$2"; }

# get ip information
get-ip-info() { curl ipinfo.io/"$1"; }

# get weather updates
get-weather() { curl -s -N http://wttr.in/"$1"; }

# kill a process based on the port number
kill-from-port() { kill -9 $(lsof -i :"$1" -t); }

# get all ec2 instances
get-ec2-instances() {
    aws ec2 describe-instances --query 'Reservations[].Instances[].[Tags[?Key==`Name`]|[0].Value,InstanceId,InstanceType,State.Name,PublicIpAddress,PrivateIpAddress]' --output table;
}

# check target group health
check-health() {
    for arn in "$(aws elbv2 describe-target-groups --query "TargetGroups[*].TargetGroupArn" --output text)"
    do
        echo "$arn"
        aws elbv2 describe-target-health --target-group-arn "$arn" --query "TargetHealthDescriptions[*].[Target.Id,TargetHealth.State]";
    done
}

#### exports
# default path
export PATH="/usr/local/bin:/usr/local/sbin:/usr/sbin:/usr/bin:/sbin:/bin"

# android
export ANDROID_SDK_ROOT="$HOME/Library/Android/sdk"
# avdmanager, sdkmanager
export PATH="$PATH:$ANDROID_SDK_ROOT/tools/bin"

# adb, logcat
export PATH="$PATH:$ANDROID_SDK_ROOT/platform-tools"

# emulator
export PATH="$PATH:$ANDROID_SDK_ROOT/emulator"

# ruby
export PATH="$HOME/.rbenv/shims:$PATH"

# rust
export PATH="$HOME/.cargo/bin:$PATH"

# openssl
export PATH="/usr/local/opt/openssl@1.1/bin:$PATH"

# homebrew
HOMEBREW_NO_ANALYTICS=1

