SUMMARY = "Media Files for tests"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/CC0-1.0;md5=0ceb3372c9595f0a8067e55da801e4a1"

inherit allarch bin_package

SRC_URI = " \
    https://developer1.toradex.com/files/toradex-dev/uploads/media/Colibri/AddSW/Linux/ReleaseTest/media-files-${PV}.tar.xz \
"
SRC_URI[md5sum] = "76482bd08f67435f34e93d44738008de"
SRC_URI[sha256sum] = "021d8261e69433221be4a3996c447411b6002c1e9b34a71c65a3bfe48b54e27d"

S = "${WORKDIR}/media-files"
