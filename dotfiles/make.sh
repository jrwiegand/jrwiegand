#!/bin/sh

export REPO_DIR="$(dirname $(readlink "$HOME"/.zshrc))"

# link directories
ln -sfv "$REPO_DIR"/.vim                     "$HOME"
ln -sfv "$REPO_DIR"/zed                      "$HOME"/.config

# link files
ln -sfv "$REPO_DIR"/.zshrc                   "$HOME"/.zshrc
ln -sfv "$REPO_DIR"/.vimrc                   "$HOME"/.vimrc
ln -sfv "$REPO_DIR"/config.toml              "$HOME"/.config/helix/config.toml
