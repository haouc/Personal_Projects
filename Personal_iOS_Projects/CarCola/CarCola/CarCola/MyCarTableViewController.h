//
//  MyCarTableViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SaveNoteView.h"
#import "MyCarTableViewCell.h"
#import "MyCar.h"

@interface MyCarTableViewController : UITableViewController
- (IBAction)cancelButton:(id)sender;

- (IBAction)editButton:(id)sender;

@property NSMutableArray *carTable;

@end
