/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from "react";
import { Platform, StyleSheet, View, NativeModules } from "react-native";

const OpenApp = Platform.OS === "android" ? NativeModules.OpenApp : undefined;

export default class App extends Component {
  componentDidMount() {
    try {
      const URL =
        "https://www.swiggy.com/?utm_source=RMZ&utm_campaign=RMZ&platform=RMZ&token=61B667C88FB270F558FB9254F94A2D73";
      const X_PLATFORM_COOKIE = "";
      /**
       * OpenApp.startSwiggy(url, x-platform-cookie)
       */
      OpenApp.startSwiggy(URL, X_PLATFORM_COOKIE);
    } catch (error) {
      console.log("Error while opening swiggy ", error);
    }
  }

  render() {
    return <View style={styles.container} />;
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  }
});
