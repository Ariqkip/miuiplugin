// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "ImagePickerMiui",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "ImagePickerMiui",
            targets: ["ImagePickerMiuiPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "ImagePickerMiuiPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ImagePickerMiuiPlugin"),
        .testTarget(
            name: "ImagePickerMiuiPluginTests",
            dependencies: ["ImagePickerMiuiPlugin"],
            path: "ios/Tests/ImagePickerMiuiPluginTests")
    ]
)