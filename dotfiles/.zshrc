ZSH_THEME="ys"
ENABLE_CORRECTION="true"
COMPLETION_WAITING_DOTS="true"
HIST_STAMPS="yyyy.mm.dd"
HOMEBREW_NO_ANALYTICS=1

export ZSH="$HOME"/.oh-my-zsh
export UPDATE_ZSH_DAYS=7
export REPO_DIR="$(dirname $(readlink "$HOME"/.zshrc))"

plugins=(
    history-substring-search
)

source "$ZSH"/oh-my-zsh.sh

unsetopt correct_all

# aliases

## replace vim with neovim
alias vim="nvim"

## extract the file from the tar
alias untar="tar -zxvf"

## did.txt
alias did='vim +"normal Go" +"r!date" +"put_" "$HOME"/did.txt'

# functions

## update functions
update() {
    local all=false
    local mac=false
    local node=false
    local rust=false
    local php=false
    local brew=false

    while [ ! $# -eq 0 ]
    do
        case "$1" in
            --all | -a)
                all=true;;
            --mac | -m)
                mac=true;;
	    --node | -n)
	        node=true;;
            --rust | -r)
                rust=true;;
            --php | -p)
                php=true;;
            --brew | -b)
                brew=true;;
        esac
        shift
    done

    if [ "$all" = true ] || [ "$mac" = true ] ; then
        echo "Updating macOS..."
        softwareupdate -i -a
    fi

    if [ "$all" = true ] || [ "$node" = true ] ; then
	echo "\nUpdating node..."
	nvm install --lts
	npm update --global
    fi

    if [ "$all" = true ] || [ "$php" = true ] ; then
        echo "\nUpdating composer..."
        composer global update
    fi

    if [ "$all" = true ] || [ "$brew" = true ] ; then
        echo "\nUpdating brew..."
        brew update
        brew upgrade --greedy
        brew cleanup --prune=all
        brew doctor
    fi
}

## find all the things
sf() { find "$1" -iname "$2"; }
ssf() { sudo find "$1" -iname "$2"; }

## find all the things in files
ss() { rg -F -i -p "$1" "$2"; }
sss() { sudo rg -F -i -p "$1" "$2"; }

## delete all files with this name
del() { find ~/ -name "$1" -type f -delete; }
sdel() { sudo find ~/ -name "$1" -type f -delete; }

## edit file or files with sed
ef() { find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \; }
sef() { sudo find "$1" -type f -exec sed -i "" "s/$2/$3/g" {} \; }

## function to update the owner of a psql database file
pdbo() {
    find "$1" -type f -exec sed -i "" "s/ Owner: $2/ Owner: $3/g" {}\;
    find "$1" -type f -exec sed -i "" "s/ $2;/$3;/g" {}\;
}

## psql function to clear the database to a usable state
pdbr() { dropdb "$1" && createdb "$1" && psql "$1" -f "$2"; }

## psql function to create a new db with the given .sql file
pdbn() { createdb "$1" && psql "$1" -f "$2"; }

## get ip information
ip() { curl https://ipinfo.io/"$1"; }

## get crypto rates
cr() { (IFS=+; curl https://rate.sx/"$*"; ); }

## kill a process based on the port number
kp() { kill -9 $(lsof -i :"$1" -t); }

## get all ec2 instances
ec2() {
    aws ec2 describe-instances --query 'Reservations[].Instances[].[Tags[?Key==`Name`]|[0].Value,InstanceId,InstanceType,State.Name,PublicIpAddress,PrivateIpAddress]' --output table --profile "$1";
}

## check target group health
ah() {
    for arn in "$(aws elbv2 describe-target-groups --query "TargetGroups[*].TargetGroupArn" --output text --profile $1)"
    do
        echo "$arn"
        aws elbv2 describe-target-health --target-group-arn "$arn" --query "TargetHealthDescriptions[*].[Target.Id,TargetHealth.State]";
    done
}

## hash content of the file line-by-line
hash_md5() {
    filename=$(basename -- "$1")
    filename="${filename%.*}"
    cat "$1" | while read line
    do
        echo -n "$line" | md5 >> "$filename"_hashed_md5.csv
    done;
}

hash_sha_256() {
    filename=$(basename -- "$1")
    filename="${filename%.*}"
    cat "$1" | while read line
    do
        echo -n "$line" | shasum -a 256 >> "$filename"_hashed_256.csv
    done;
}

## check status on a given url and notify
cs() {
    local status_result=$(curl -I -L -s "$1" | rg 200)
    if [ -z "$status_result" ]
    then

    else
        terminal-notifier -title "Success!" -message "Click to open URL" -open "$1"
    fi
}

## generate lorem text
lorem() {
    local paragraphs="${1}";
    local sentences="${2}";

    if [ "$paragraphs" = "" ]; then
        local paragraphs=1;
    fi
    if [ "$sentences" = "" ]; then
	local sentences=1;
    fi

    curl -s http://metaphorpsum.com/paragraphs/"${paragraphs}"/"${sentences}" | pbcopy

    echo "Saved to clipboard..."
}

# exports
## base
export PATH="/usr/local/bin:/usr/local/sbin:/usr/sbin:/usr/bin:/sbin:/bin"

## java
export JAVA_HOME="/usr/local/Cellar/openjdk@8/1.8.0+312/libexec/openjdk.jdk/Contents/Home"

## node (via nvm)
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion

## php
export PATH="/usr/local/opt/php@7.4/bin:$PATH"
export PATH="/usr/local/opt/php@7.4/sbin:$PATH"

## ruby
export PATH="$HOME/.rbenv/shims:$PATH"

## rust
export PATH="$HOME/.cargo/bin:$PATH"

## composer
export PATH="$HOME/.composer/vendor/bin:$PATH"

## android
export ANDROID_SDK_ROOT="$HOME/Library/Android/sdk"

### avdmanager, sdkmanager
export PATH="$ANDROID_SDK_ROOT/tools/bin:$PATH"

### adb, logcat
export PATH="$ANDROID_SDK_ROOT/platform-tools:$PATH"

### emulator
export PATH="$ANDROID_SDK_ROOT/emulator:$PATH"

### flutter
export PATH="$HOME/Library/Flutter/sdk/bin:$PATH"

### chrome/brave
export CHROME_EXECUTABLE="/Applications/Brave Browser.app/Contents/MacOS/Brave Browser"

## openssl
export PATH="/usr/local/opt/openssl@1.1/bin:$PATH"