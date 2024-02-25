#!/bin/sh

export REPO_DIR="$(dirname $(readlink "$HOME"/.zshrc))"

if [ ! -d "$HOME"/.nvm ]; then
    mkdir "$HOME"/.nvm
fi

# link all the things
ln -sfv "$REPO_DIR"/.zshrc            "$HOME"/.zshrc
ln -sfv "$REPO_DIR"/.vimrc            "$HOME"/.vimrc
ln -sfv "$REPO_DIR"/.vim              "$HOME"/.vim
ln -sfc "$REPO_DIR"/config.toml       "$HOME"/.config/helix/config.toml
