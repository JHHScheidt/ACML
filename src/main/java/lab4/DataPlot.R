## 
# Requires 'rstudioapi' & 'ggplot2'
library(rstudioapi)
library(ggplot2)

setwd(dirname(dirname(dirname(dirname(rstudioapi::getActiveDocumentContext()$path)))))
setwd("data")
directory <- getwd()


actions <- read.csv(file="PositionVSVelocityHeatAction.txt")
qVals <- read.csv(file="PositionVSVelocityHeatQValues.txt")
timeSteps <- read.csv(file="EpisodesVSTimestep.txt")
colnames(actions) <- c("Position", "Velocity","Action")
colnames(qVals) <- c("Position", "Velocity", "QValue")
colnames(timeSteps) <- c("Episode", "Timestep")

actionPlot <- ggplot(actions, aes(x=Position, y=Velocity, color=Action)) + 
  geom_point() + scale_colour_gradient2(low="blue", mid="white", high="orange", midpoint = 1.0) 
qValsPlot <- ggplot(qVals, aes(x=Position, y=Velocity, color=QValue)) + 
  geom_point() + scale_colour_gradient2(low="blue", mid="white", high="orange")
timestepPlot <- ggplot(timeSteps, aes(x=Episode, y=Timestep)) + 
  geom_point() + geom_smooth(method="loess")
show(actionPlot)
show(qValsPlot)
show(timestepPlot)
