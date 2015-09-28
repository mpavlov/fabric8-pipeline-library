def updateFabric8ReleaseDeps = ""
try {
  updateFabric8ReleaseDeps = UPDATE_FABRIC8_RELEASE_DEPENDENCIES
} catch (Throwable e) {
  updateFabric8ReleaseDeps = "${env.UPDATE_FABRIC8_RELEASE_DEPENDENCIES ?: 'false'}"
}

def release = ""
try {
  release = IS_RELEASE
} catch (Throwable e) {
  release = "${env.IS_RELEASE ?: 'true'}"
}

stage 'canary release kubernetes-model'
releaseKubernetesModel{
  isRelease = release
}

stage 'wait for kubernetes-model to be synced with maven central'
waitUntilArtifactSyncedWithCentral {
  artifact = 'kubernetes-model'
}
