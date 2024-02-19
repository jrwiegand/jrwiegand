export RUST=false
if type rustc > /dev/null; then
	export RUST=true
fi

export CARGO=false
if type cargo > /dev/null; then
	export CARGO=true
fi
