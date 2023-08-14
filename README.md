# ReFlowLab

=================



ReFlowLab is a toolkit for the calculation of capital costs of redox flow batteries. 


**Features**

- adaptable redox flow battery database, including:
	- active material properties
	- solvent properties
	- stack component properties
- capital cost calculation, under variation of: 
	- electrolyte
	- stack
	- working point
- full cell as well as half-cell systems consideration in calculation

- Future updates and bug fixes are planned




**Installation**

This project is setup as Maven project. All dependencies are setup by the provided pom file. A compiled version is available on request.



**Documentation**

This tool is part of a publication currently undergoing submission process.
___
 
 
Adding Active Materials to Database - The following parameters need to be known for proper calculation:
- Mechanism of electrochemical reaction (number of transfered electrons | number of transfered protons at defined pH value)
- Solubility
- Redox potential
- Diffusion coefficient
- Reaction rate constant + charge transfer coefficient
- Capacity fade rate / capacity retention
- In case of organic solvents: Density, dyn./kin. viscosity as well as electrochemical stability window of selected solvent



**License**

ReFlowLab is an open source toolkit to calculate the capital costs of redox flow batteries. It is licensed under the `MIT license`_, a permissive, business-friendly license for open source
software.

.. _`MIT license`: https://github.com/Domeml94/ReFlowLab/blob/master/LICENSE