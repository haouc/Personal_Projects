//
//  GalleryTableViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/10/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GalleryTableViewController.h"
#import "SharedNetworking.h"
#import "MyCarTableViewCell.h"

@interface GalleryTableViewController : UITableViewController

@property (strong, nonatomic) NSMutableArray *data;
@property NSMutableDictionary *links;

@property NSMutableArray *imgArr;


//@property (weak, nonatomic) id<loadDataFinishDelegate> delegate;

- (IBAction)leaveGalleryButton:(id)sender;


@end
