export UPDATE_CARGO=false
if [ -d "$HOME/.cargo/bin" ]; then
	export CARGO_HOME="$HOME/.cargo"
	export PATH="$CARGO_HOME/bin:$PATH"
	export UPDATE_CARGO=true
fi

export UPDATE_RUSTUP=false
if [ -d "$HOME/.rustup" ]; then
  export RUSTUP_HOME="$HOME/.rustup"
	export UPDATE_RUSTUP=true
fi
