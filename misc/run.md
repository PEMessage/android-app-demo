

# Guide
```bash
# See: https://gist.github.com/mrk-han/66ac1a724456cadf1c93f4218c6060ae

# a2p /opt/android-sdk/tools/bin # ERROR, not using old sdkmanager !!
a2p /opt/android-sdk/cmdline-tools/latest/bin

px2 # setup proxy

# q-sudo pass http_proxy to it
q-sudo sdkmanager --install "system-images;android-33;google_atd;arm64"

avdmanager --verbose create avd --force --name "api33_google_atd_emulator" --package "system-images;android-33;google_atd;arm64-v8a" --tag "google_atd" --abi "arm64-v8a" --device "pixel_5"

a2p /opt/android-sdk/emulator # add emulator to PATH

export ANDROID_SDK_ROOT=/opt/android-sdk # emulator require setup SDK_ROOT or ANDROID_HOME
emulator -list-avds # check avds just create 

emulator @api33_google_atd_emulator # but still FAIL
# PANIC: Avd's CPU Architecture 'arm64' is not supported by the QEMU2 emulator on x86_64 host.

# --- update ---
# See: https://stackoverflow.com/a/74819011/20689546 # Only support <= 28 for arm64 on x86_64
# See: https://stackoverflow.com/a/67994902/20689546 # the arm64 images on api28-31 are meant for M1 host.
    # https://issuetracker.google.com/issues/210099270#comment2 

```

# AVDManager Tips

`avdmanager list avd`: check avd
`q-sudo avdmanager delete avd -n api33_google_atd_emulator`: rm it

