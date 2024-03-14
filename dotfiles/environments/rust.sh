export CARGO=false
if [ -d "$HOME/.cargo/bin" ]; then
	export CARGO=true
	export CARGO_HOME="$HOME/.cargo"
	export PATH="$CARGO_HOME/bin:$PATH"
fi

export RUSTUP=false
if [ -d "$HOME/.rustup" ]; then
	export RUSTUP=true
	export RUSTUP_HOME="$HOME/.rustup"
fi
