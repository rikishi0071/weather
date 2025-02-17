# RadarWeather

This application is forked from Privacy Friendly Weather (https://github.com/SecUSo/privacy-friendly-weather) a privacy friendly weather app.
The original function has been modified to support the new OpenWeather One Call API, which provides a lot more features, like precipitation forecast for the next 60 minutes,
hourly forecasts for the next 2 days, 8 day week forecasts, etc. In addition a rain radar functionality powered by RainViewer API (https://www.rainviewer.com/api.html) has been added. More weather categories were added, most images and icons were replaced. RadiusSearch now also shows the results on a map with weather icons.
The widgets have been removed. A chart showing forecasts with min/max temperature and precipitation for the next week has been added.

As One Call API only allows 1000 calls per day an own OpenWeatherMap API key is mandatory.

Please register for free account at: https://home.openweathermap.org/users/sign_up

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" height="75">](https://f-droid.org/de/packages/org.woheller69.weather/)

## Building 

Further development requires Android Studio, we recommend to use at least version 2.2.2

### API Reference

Minimum SDK: 17
Target SDK: 25

## License

This app - like the original app Privacy Friendly Weather - is licensed under the GPLv3.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.


The app also uses:
- Icons from [Google Material Design Icons](https://material.io/resources/icons/) licensed under Apache License Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
- Leaflet which is licensed under the very permissive 2-clause BSD License. (https://github.com/Leaflet/Leaflet/blob/master/FAQ.md)
- Leaflet.TileLayer.ColorFilter which is licensed under MIT License. (https://github.com/xtk93x/Leaflet.TileLayer.ColorFilter/blob/master/LICENSE)
- RainViewer API which is free (https://www.rainviewer.com/api.html) & RainViewer API Example (https://github.com/rainviewer/rainviewer-api-example)
- WilliamChart (com.db.chart) (https://github.com/diogobernardino/williamchart) which is licensed under Apache License Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
- Android SQLiteAssetHelper (com.readystatesoftware.sqliteasset) which is licensed under Apache License Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
- Map data from OpenStreetMap, licensed under the Open Data Commons Open Database License (ODbL) by the OpenStreetMap Foundation (OSMF) (https://www.openstreetmap.org/copyright)

## Contributing

If you find a bug, please open an issue in the Github repository, assuming one does not already exist.
  - Clearly describe the issue including steps to reproduce when it is a bug. In some cases screenshots can be supportive.
  - Make sure you mention the Android version and the device you have used when you encountered the issue.
  - Make your description as precise as possible.

If you know the solution to a bug please report it in the corresponding issue and if possible modify the code and create a pull request.