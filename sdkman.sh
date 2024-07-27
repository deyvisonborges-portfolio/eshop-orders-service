curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

sdk install java 17.0.1-open

sdk default java 17.0.1-open

sdk env init

touch .sdkmanrc
echo "java=17.0.1-open" > .sdkmanrc

sdk env