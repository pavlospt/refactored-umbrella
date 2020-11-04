rootProject.name = "RefactoredUmbrella"
include(
    ":app",
    ":android-core:android-core-adapter",
    ":android-core:android-core-viewbinding",

    ":core:core-dispatchers",
    ":core:core-domain",
    ":core:core-result",
    ":core:core-usecase",

    ":github-stack:db-github",
    ":github-stack:localrepo-github",
    ":github-stack:remoterepo-github",
    ":github-stack:usecase-github",

    ":ui:ui-dashboard",
    ":ui:ui-home",
    ":ui:ui-design-system",
    ":ui:ui-navigation"
)
