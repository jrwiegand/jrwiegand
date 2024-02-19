export ANDROID=false
if [ -d "$HOME/Library/Android" ]; then
	export ANDROID_HOME="$HOME/Library/Android/sdk"
	export ANDROID_SDK_ROOT="$HOME/Library/Android/sdk"

	### avdmanager, sdkmanager
	export PATH="$ANDROID_SDK_ROOT/tools/bin:$PATH"

	### adb, logcat
	export PATH="$ANDROID_SDK_ROOT/platform-tools:$PATH"

	### emulator
	export PATH="$ANDROID_SDK_ROOT/emulator:$PATH"

	if [ -d "/Applications/Brave Browser.app" ]; then
		export CHROME_EXECUTABLE="/Applications/Brave Browser.app/Contents/MacOS/Brave Browser"
	elif [ -d "/Applications/Google Chrome.app"]; then
		export CHROME_EXECUTABLE="/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
	fi
	export ANDROID=true
fi

export FLUTTER=false
if [ -d "$HOME/Library/Flutter" ]; then
	export PATH="$HOME/Library/Flutter/bin:$PATH"
	export FLUTTER=true
fi
