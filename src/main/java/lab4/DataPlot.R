## 
# Requires 'rstudioapi' & 'ggplot2'
library(rstudioapi)
library(ggplot2)

setwd(dirname(dirname(dirname(dirname(rstudioapi::getActiveDocumentContext()$path)))))
setwd("data")
directory <- getwd()


actions <- read.csv(file="PositionVSVelocityHeatAction.txt")
qVals <- read.csv(file="PositionVSVelocityHeatQValues.txt")
colnames(actions) <- c("Position", "Velocity","Action")
colnames(qVals) <- c("Position", "Velocity", "QValue")

actionPlot <- ggplot(actions, aes(x=Position, y=Velocity, color=Action)) + 
  geom_point() 
qValsPlot <- ggplot(qVals, aes(x=Position, y=Velocity, color=QValue)) + 
  geom_point() + scale_color_manual(breaks = c("2", "1", "0"),
                                    values=c("red", "black", "green"))
show(actionPlot)
show(qValsPlot)
