import { FunctionComponent } from "react";
import { Props } from "../types";


const Main: FunctionComponent = ({ children }: Props) => {
  return <main className="container">{children}</main>;
};

export default Main;
