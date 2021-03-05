FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"

SRC_URI += " \
    file://disable_systemd_coloroutput.sh \
    file://x11-display-var.sh \
    file://wayland-env.sh \
"

do_install_append () {
    install -m 0755 -d ${D}${sysconfdir}/profile.d
    install -m 0644 ${WORKDIR}/disable_systemd_coloroutput.sh ${D}${sysconfdir}/profile.d/
    install -m 0644 ${WORKDIR}/x11-display-var.sh ${D}${sysconfdir}/profile.d/
    install -m 0644 ${WORKDIR}/wayland-env.sh ${D}${sysconfdir}/profile.d/
}

BASEFILESISSUEINSTALL = ""
