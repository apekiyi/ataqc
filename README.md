# ATA-QC: Real-Time Seismic Quality Control Tool

ATA-QC is a Java-based software application developed for the instantaneous quality control (QC) of raw seismic data, specifically focusing on NFH and SEG-D formats.

## Key Features
- **Real-Time QC:** Instantaneous quality control for NFH and SEG-D raw data.
- **Format Support:** Specialized handling of near-field hydrophone and standard SEG-D records.
- **Lightweight Design:** Optimized for fast execution on standard workstations (Minimum 4GB RAM).

## System Requirements
- **Java Version:** Java 17 or higher (64-bit).
- **Memory (RAM):** - *Minimum:* 4 GB
  - *Recommended:* 16 GB or higher for large seismic datasets.
- **Operating System:** Windows 10/11, macOS, or Linux.

## Installation & Usage
1. Ensure **Java 17** or higher is installed on your system.
2. Download the `ATAQC.jar` file from this repository.
3. You can directly double-click the `ATAQC.jar` file to start the application.

## Data Structure for Testing
For the software to recognize and process files correctly, please maintain the following directory structure in the same folder as the `.jar` file:

* **NFH Data:** Must be placed in `data/NFH/(project_name)/`
* **Raw SEG-D Data:** Must be placed in `data/SEGD/(project_name)/`

## License
This project is licensed under the **MIT License**.

## Contact
**Alican Pekiyi** - [alican.pekiyi@mta.gov.tr]
