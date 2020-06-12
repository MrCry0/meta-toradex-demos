require graphical-tdx-image.bb

SUMMARY = "Toradex Embedded Linux Verification Image"
DESCRIPTION = "Image for BSP verification"

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "Verification-Image"

IMAGE_INSTALL += " \
    packagegroup-tdx-cli \
    packagegroup-tdx-graphical \
    \
    bash \
    coreutils \
    less \
    makedevs \
    mime-support \
    util-linux \
    v4l-utils \
    \
    gpicview \
    media-files \
"
